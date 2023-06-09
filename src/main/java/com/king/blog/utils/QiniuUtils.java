package com.king.blog.utils;

import com.alibaba.fastjson.JSON;
import com.qiniu.common.QiniuException;
import com.qiniu.http.Response;
import com.qiniu.storage.BucketManager;
import com.qiniu.storage.Configuration;
import com.qiniu.storage.Region;
import com.qiniu.storage.UploadManager;
import com.qiniu.storage.model.DefaultPutRet;
import com.qiniu.storage.model.FileInfo;
import com.qiniu.storage.model.FileListing;
import com.qiniu.util.Auth;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

@Component
public class QiniuUtils {

    //    public static  final String url = "https://static.mszlu.com/"; http://rv0a9iouf.bkt.clouddn.com/vue%E7%94%9F%E5%91%BD%E5%91%A8%E6%9C%9F%E5%9B%BE%E7%89%87.png
    public static final String url = "http://rv0a9iouf.bkt.clouddn.com/";
    //    @Value("${bokexiangmu.accessKey}")
    private String accessKey = "o-Jprj_ByBSodDHZaGBrruceuiFCaDlAHFdy517U";
    //    @Value("${bokexiangmu.accessSecretKey}")
    private String accessSecretKey = "o-Jprj_ByBSodDHZaGBrruceuiFCaDlAHFdy517U";

    public boolean upload(MultipartFile file, String fileName) {

        //构造一个带指定 Region 对象的配置类
        Configuration cfg = new Configuration(Region.huabei());
        //...其他参数参考类注释
        UploadManager uploadManager = new UploadManager(cfg);
        //...生成上传凭证，然后准备上传
        String bucket = "mszlu";
        //默认不指定key的情况下，以文件内容的hash值作为文件名
        try {
            byte[] uploadBytes = file.getBytes();
            Auth auth = Auth.create(accessKey, accessSecretKey);
            String upToken = auth.uploadToken(bucket);
            Response response = uploadManager.put(uploadBytes, fileName, upToken);
            //解析上传成功的结果
            DefaultPutRet putRet = JSON.parseObject(response.bodyString(), DefaultPutRet.class);
            return true;
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return false;
    }

//    public void deleteAll() throws QiniuException {
//        String bucket = "wwwmszlucom";
//        Auth auth = Auth.create(accessKey, accessSecretKey);
//        //构造一个带指定 Region 对象的配置类
//        Configuration cfg = new Configuration(Region.huabei());
//        BucketManager bucketManager = new BucketManager(auth, cfg);
//        FileListing fileListing = bucketManager.listFiles(bucket, "/", "", 1000, "");
//        FileInfo[] items = fileListing.items;
//        for (FileInfo info : items) {
//
//        }
//    }
}
