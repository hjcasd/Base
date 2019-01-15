package com.hjc.base.utils.helper;

/**
 * @Author: HJC
 * @Date: 2019/1/7 11:33
 * @Description: 百度地图定位的工具类
 */
public class LocationProvider {
//    public LocationClient mLocationClient;
//    public LocationListener mLocationListener;
//
//    public LocationProvider(Context context) {
//        mLocationClient = new LocationClient(context);                     //声明LocationClient类
//    }
//
//    public void initLocation() {
//        LocationClientOption option = new LocationClientOption();
//
//        //可选，默认高精度，设置定位模式，高精度，低功耗，仅设备
//        option.setLocationMode(LocationClientOption.LocationMode.Hight_Accuracy);
//
//        //可选，默认gcj02，设置返回的定位结果坐标系
//        option.setCoorType("bd09ll");
//
//        //可选，设置是否需要地址信息，默认不需要
//        option.setIsNeedAddress(true);
//
//        //可选，默认false,设置是否使用gps
//        option.setOpenGps(true);
//
//        //可选，默认false，设置是否当GPS有效时按照1S/1次频率输出GPS结果
//        option.setLocationNotify(true);
//
//        //可选，默认false，设置是否需要位置语义化结果，
//        //可以在BDLocation.getLocationDescribe里得到，结果类似于“在北京天安门附近”
//        option.setIsNeedLocationDescribe(true);
//
//        //可选，默认false，设置是否需要POI结果，可以在BDLocation.getPoiList里得到
//        option.setIsNeedLocationPoiList(true);
//
//        //可选，默认true，定位SDK内部是一个SERVICE，并放到了独立进程，设置是否在stop的时候杀死这个进程，默认不杀死
//        option.setIgnoreKillProcess(false);
//
//        mLocationClient.setLocOption(option);
//
//        mLocationClient.start();
//
//        mLocationClient.registerLocationListener(new BDLocationListener() {
//            @Override
//            public void onReceiveLocation(BDLocation location) {
//                if (mLocationListener != null) {
//                    mLocationListener.getLocation(location);
//                    mLocationClient.stop();
//                }
//            }
//
//            @Override
//            public void onConnectHotSpotMessage(String s, int i) {
//            }
//        });
//    }
//
//    public interface LocationListener {
//        void getLocation(BDLocation bdLocation);
//    }
//
//    /**
//     * 回调位置方法
//     */
//    public void setLocationListener(LocationListener locationListener) {
//        this.mLocationListener = locationListener;
//    }

}
