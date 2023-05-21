package com.king.blog.service.impl;

import com.king.blog.mapper.ArticleBodyMapper;
import com.king.blog.mapper.ArticleMapper;
import com.king.blog.mapper.ArticleTagMapper;
import com.king.blog.pojo.Article;
import com.king.blog.pojo.ArticleBody;
import com.king.blog.pojo.ArticleTag;
import com.king.blog.pojo.SysUser;
import com.king.blog.service.*;

import com.king.blog.service.ThreadService;
import com.king.blog.utils.UserThreadLocal;
import com.king.blog.vo.*;

import com.king.blog.vo.dos.Archives;
import com.king.blog.vo.params.ArticleParam;
import org.joda.time.DateTime;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
public class ArticleServiceImpl implements ArticleService {
    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private TagService tagService;
    @Autowired
    private SysUserService sysUserService;
    @Autowired
    private ArticleBodyService articleBodyService;
    @Autowired
    private CategoryService categoryService;

    @Autowired
    private ThreadService threadService;

    @Autowired
    private ArticleTagMapper articleTagMapper;

    @Autowired
    private ArticleBodyMapper articleBodyMapper;

    /**
     * 首页分页查询
     *
     * @param pageParams
     * @return
     */
//    @Override
//    public List<ArticleVo> selectPages(PageParams pageParams) {
//        int page = pageParams.getPage();
//        int pageSize = pageParams.getPageSize();
//        List<Article> records = articleMapper.selectPage((page - 1) * pageSize, pageSize);
//        // 这里需要使用vo类 对数据进行遍历
//        List<ArticleVo> articleVoList = copyList(records,true,true);
//        return  articleVoList;
//    }
    // 分页查询之多条件查询
    @Override
    public List<ArticleVo> selectPages(PageParams pageParams) {


        // 当查询遍历完成之后把数据封装到集合中去
//        List<Long> articleTagId = new ArrayList<>();

        // 判断tagId 是否为空 如果为空就
//        if (pageParams.getTagId() != null) {
//            Long tagId = pageParams.getTagId();
//            List<ArticleTag> articleTags = articleTagMapper.selectAllById(tagId);
//            for (ArticleTag articleTag : articleTags){
//                articleTagId.add(articleTag.getArticleId());
//            }
//        }
//        System.out.println(articleTagId);

        int page = pageParams.getPage();
        int pageSize = pageParams.getPageSize();
        Long categoryId = pageParams.getCategoryId();
        Long tagId = pageParams.getTagId();
        String year = pageParams.getYear();
        String month = pageParams.getMonth();
        List<Article> records = articleMapper.selectPages((page - 1) * pageSize, pageSize, categoryId, tagId, year, month);
        // 这里需要使用vo类 对数据进行遍历
        List<ArticleVo> articleVoList = copyList(records, true, true);
        return articleVoList;
    }


    /**
     * 根据limit 查询数据
     *
     * @param limit
     * @return
     */
    @Override
    public Result selectLimit(int limit) {
        List<Article> articles = articleMapper.selectsLimit(limit);
        return Result.success(copyList(articles, false, false));
    }


    /**
     * 最新文章查询
     *
     * @param limit
     * @return
     */
    @Override
    public Result selectLimits(int limit) {
        List<Article> articles = articleMapper.selectsLimits(limit);
        return Result.success(copyList(articles, false, false, false, false));
    }

    /**
     * 文章归档
     *
     * @return
     */
    @Override
    public Result listArchives() {
        List<Archives> archivesList = articleMapper.listArchives();
        return Result.success(archivesList);
    }

    /**
     * 文章详情
     * 分析 文章详情 存在三张表中
     * 每一张表都要查询
     *
     * @param articleId
     * @return
     */
    @Override
    public ArticleVo findArticleById(Long articleId) {
        // 分析 ：
        // 第一步：查询用户的数据
        // 先把用户的id 查询出来
        // 根据用户id查询文章信息
        Article article = articleMapper.selectById(articleId);
        // 把数据赋值到vo类中去
        // 第二步：根据bodyId 和 catrgoryId 去做关联查询
        ArticleVo articleVo = copy(article, true, true, true, true);

        // 用户浏览完文章 就要增加一个浏览量
        // 问题
        // 用户查看完文章之后 本应该直接返回数据了 这个时候做一个更新操作
        // 更新时写锁 就会阻塞其他的读操作 性能就会降低
        // 更新 增加了此次借口的耗时
        // 跟新 增加此次接口的耗时 如果一旦更新出了问题 不能影响查看文章的操作
        // 使用线程池解决  可以把更新操作扔到线程池中  这样就不干扰主线程了

        threadService.updateArticleViewCount(articleMapper, article);


        return articleVo;
    }

    /**
     * 发布文章
     *
     * @param articleParam 根据前端页码封装的数据
     * @return
     */
    @Override
//    @Transactional
    public Result publish(ArticleParam articleParam) {
        /**
         * 发布文章的业务逻辑
         *  1. 第一步：拿到用户数据 这个数据在jwt中存储
         *  2. 第二步：创建Article 对象给这个对象赋值 然后插入数据'
         *  3. 第三步：将标签加入到关联表中去
         */
        // 第一步：拿到用户的id // 通过这个线程那的话需要把这个请求放到拦截器中
        SysUser sysUser = UserThreadLocal.get();
        // 发布文章 目的 构建Article 对象
        // 根据用户的id发布
        Article article = new Article(); // 文章表、
        // 把用户的id 放入进去
//        // 插入用户数据 插入之后就能获取一个文章的id
//        this.articleMapper.insert(article);
        article.setAuthorId(sysUser.getId());
        article.setCategoryId(articleParam.getCategory().getId());
        article.setCreateDate(System.currentTimeMillis());
        article.setCommentCounts(0);
        article.setSummary(articleParam.getSummary());
        article.setTitle(articleParam.getTitle());
        article.setViewCounts(0);
        article.setWeight(Article.Article_Common);
//        article.setBodyId(-1L);

        // 插入之后会生成一个文章id
        this.articleMapper.insert(article);


        // tag
        // 通过articleParam 封装的Vo类 拿到文章的id
        List<TagVo> tags = articleParam.getTags();
        // 拿到标签之后进行判断一下
        if (tags != null) {
            // 对TagVo 进行for 循环
            for (TagVo tag : tags) {
                // 插入成功之后这里就能获取文章id
                Long articleId = article.getId();
                // 拿到文章id 之后把他加入到关联列表当中去
                // article_tag 这张表就能对他进行增删改查了
                // 把拿到的文章数据插入到表中去
                // 创建对象把数据封装到articleTag表中去
                ArticleTag articleTag = new ArticleTag();
                // 拿到标签的id
//                articleTag.setTagId(Long.parseLong(tag.getId()));
                articleTag.setTagId(tag.getId());
                // 拿到用户的id
                articleTag.setArticleId(articleId);
                //
                articleTagMapper.insert(articleTag);
            }
        }
        // body 文章内容存储
        ArticleBody articleBody = new ArticleBody();
        // 通过 article 表向 articleBody 表中添加数据
        articleBody.setArticleId(article.getId()); // 文章id
        articleBody.setContent(articleParam.getBody().getContent());
        articleBody.setContentHtml(articleParam.getBody().getContentHtml());
        // 插入数据
        articleBodyMapper.insert(articleBody);
        // 插入数据之后需要更新
        article.setBodyId(articleBody.getId());
        articleMapper.updateById(article);

        // 前端需要返回map 集合的数据
        Map<String, String> map = new HashMap<>();
        map.put("id", article.getId().toString());
        return Result.success(map);
    }

    @Autowired
    private StringRedisTemplate redisTemplate;

    /**
     * 文章详情
     *
     * @param articleId
     * @return
     */
    @Override
    public Result findArticleByIds(Long articleId) {
        /**
         * 1. 根据id查询 文章信息
         * 2. 根据bodyId和categoryid 去做关联查询
         */
        Article article = this.articleMapper.selectById(articleId);
        ArticleVo articleVo = copy(article, true, true, true, true);
        //查看完文章了，新增阅读数，有没有问题呢？
        //查看完文章之后，本应该直接返回数据了，这时候做了一个更新操作，更新时加写锁，阻塞其他的读操作，性能就会比较低
        // 更新 增加了此次接口的 耗时 如果一旦更新出问题，不能影响 查看文章的操作
        //线程池  可以把更新操作 扔到线程池中去执行，和主线程就不相关了
        threadService.updateArticleViewCount(articleMapper, article);

        String viewCount = (String) redisTemplate.opsForHash().get("view_count", String.valueOf(articleId));
        if (viewCount != null) {
            articleVo.setViewCounts(Integer.parseInt(viewCount));
        }
        return Result.success(articleVo);
    }

    /**
     * 定义 ArticleVo 返回给前端数据
     * 需要的参数  records
     *
     * @param records 用户数据
     * @return
     */
//     定义vo类 遍历vo 类
    private List<ArticleVo> copyList(List<Article> records, boolean isTag, boolean isAuthor) {
        List<ArticleVo> articleVoList = new ArrayList<>();
        for (Article record : records) {
            articleVoList.add(copy(record, isTag, isAuthor, false, false));
        }
        return articleVoList;
    }

    private List<ArticleVo> copyList(List<Article> records, boolean isTag, boolean isAuthor, boolean isBody, boolean isCategory) {
        List<ArticleVo> articleVoList = new ArrayList<>();
        for (Article record : records) {
            articleVoList.add(copy(record, isTag, isAuthor, isBody, isCategory));
        }
        return articleVoList;
    }

    /**
     * 封装vo类
     *
     * @param article    首页表
     * @param isTag      用户作者表 和 作者名称返回
     * @param isAuthor   作者名称
     * @param isBody     需要article中的数据
     * @param isCategory 这里需要信息中的数据
     * @return
     */
    private ArticleVo copy(Article article, boolean isTag, boolean isAuthor, boolean isBody, boolean isCategory) {
        ArticleVo articleVo = new ArticleVo();
        BeanUtils.copyProperties(article, articleVo);
//        articleVo.setCreateDate(new DateTime(article.getCreateDate()).toString("yyyy-MM-dd HH:mm"));
        articleVo.setCreateDate(new DateTime(article.getCreateDate()).toString("yyyy-MM-dd HH:mm"));

        // 封装数据
        if (isTag) {
            // 获取文章的id
            Long articleId = article.getId();
            List<TagVo> tagByArticleId = tagService.findTagByArticleId(articleId);
            System.out.println(tagByArticleId);
            // 根据用户的id 查询作者的信息 vo 表
//            articleVo.setTags(tagService.findTagByArticleId(articleId));
            articleVo.setTags(tagByArticleId);
        }
        if (isAuthor) {
//            Long authorId = article.getAuthorId();
//            SysUser sysUser = sysUserService.fingUserById(authorId);
//            UserVo userVo = new UserVo();
//            userVo.setAvatar(sysUser.getAvatar());
//            userVo.setId(sysUser.getId().toString());
//            userVo.setNickname(sysUser.getNickname());
//            articleVo.setAuthor(userVo);
            Long authorId = article.getAuthorId();
            System.out.println(authorId);
            SysUser sysUser = sysUserService.fingUserById(authorId);
            sysUser.getNickname();
            System.out.println(sysUser);
//            articleVo.setAuthor(sysUserService.fingUserById(authorId).getNickname());
            articleVo.setAuthor(sysUser.getNickname());
        }
        if (isBody) {
            // 第一步：获取到id信息 然后再查询article表中的数据
            Long bodyId = article.getBodyId();
            // 第二步：根据bodyId 查询数据 // 让他去调用ArticleBodyMapper接口
            articleVo.setBody(articleBodyService.selectBodyId(bodyId));
        }
        if (isCategory) {
            Long categoryId = article.getCategoryId();
            articleVo.setCategoryVo(categoryService.selectById(categoryId));
        }

        return articleVo;
    }

}






































