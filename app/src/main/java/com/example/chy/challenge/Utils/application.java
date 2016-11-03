package com.example.chy.challenge.Utils;

import android.app.Application;
import android.app.Service;
import android.content.Context;
import android.content.SharedPreferences;
import android.os.Vibrator;
import android.util.Log;

import com.example.chy.challenge.NetInfo.LocationService;


/**
 * Created by SJL on 2016/11/01.
 */
public class application extends Application {
    private static application  mInstance = null;
    public static int UID;
    public static String isFrist="yes";
    public LocationService locationService;
    public Vibrator mVibrator;

    @Override
    public void onCreate() {
        super.onCreate();
        //极光推送初始化
       // JPushInterface.setDebugMode(true);
       // JPushInterface.init(this);
        mInstance = this;
        //initImageLoader(getApplicationContext());
        SharedPreferences sp=getSharedPreferences("UserUID", Context.MODE_PRIVATE);
        UID=sp.getInt("UID", 0);
        isFrist=sp.getString("isFrist", "");
        Log.e("hou", "APPlication:UID=" + UID);

        /***
         * 初始化定位sdk，建议在Application中创建
         */
        locationService = new LocationService(getApplicationContext());
        mVibrator =(Vibrator)getApplicationContext().getSystemService(Service.VIBRATOR_SERVICE);
    }
    public static application getInstance() {
        return mInstance;
    }
    /**
     * 初始化ImageLoader
     */
//    public static void initImageLoader(Context context) {
//        File cacheDir = StorageUtils.getOwnCacheDirectory(context, "wxt_parent/Cache");// 获取到缓存的目录地址
//        // 创建配置ImageLoader(所有的选项都是可选的,只使用那些你真的想定制)，这个可以设定在APPLACATION里面，设置为全局的配置参数
//        ImageLoaderConfiguration config = new ImageLoaderConfiguration.Builder(context).memoryCacheExtraOptions(1000, 1000)
//                // max width,max height，即保存的每个缓存文件的最大长宽default=device screen dimensions
//                .discCacheExtraOptions(1000, 1000, Bitmap.CompressFormat.JPEG, 75, null)
//                        // Can slow ImageLoader, use it carefully (Better don't use
//                        // it)设置缓存的详细信息，最好不要设置这个
//                .threadPoolSize(5)// 线程池内加载的数量
//                .threadPriority(Thread.NORM_PRIORITY - 1).tasksProcessingOrder(QueueProcessingType.FIFO) // default
//                .denyCacheImageMultipleSizesInMemory()
//                        // .memoryCache(new UsingFreqLimitedMemoryCache(2 * 1024 *
//                        // 1024))
//                        // You can pass your own memory cache
//                        // .memoryCache(new LruMemoryCache(2 * 1024 * 1024))
//                .memoryCache(new WeakMemoryCache())
//                        // implementation你可以通过自己的内存缓存实现
//                .memoryCacheSize(2 * 1024 * 1024).memoryCacheSizePercentage(13) // default
//                .discCacheSize(50 * 1024 * 1024)
//                        // .discCacheFileNameGenerator(new Md5FileNameGenerator())
//                        // 将保存的时候的URI名称用MD5加密
//                        // .discCacheFileNameGenerator(new
//                        // HashCodeFileNameGenerator())// 将保存的时候的URI名称用HASHCODE加密
//                .tasksProcessingOrder(QueueProcessingType.LIFO).discCacheFileCount(1000) // 缓存的File数量
//                .discCache(new UnlimitedDiscCache(cacheDir))// 自定义缓存路径
//                .defaultDisplayImageOptions(DisplayImageOptions.createSimple()).imageDownloader(new BaseImageDownloader(context, 5 * 1000, 30 * 1000))
//                        // connectTimeout (5s), readTimeout(30s)超时时间
//                .writeDebugLogs() // Remove for release app
//                .build();
//        ImageLoader.getInstance().init(config);// 全局初始化此配置
 //   }

}
