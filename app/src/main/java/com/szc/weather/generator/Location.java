package com.szc.weather.generator;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

/**
 * Created by szc on 17-2-26.
 */

public interface Location {
    //http://api.map.baidu.com/geocoder/v2/?
    // ak=sNt22yxtnLihNl78HTasb7vzcbvEYZK0
    // &location=31.298247284063569,120.66298796130684
    // &output=json&
    // pois=0&
    // mcode=27:09:CB:86:B5:F9:30:AF:20:E0:FA:E0:4B
    @GET("v2/")
    Call<String> getCity(@Query("ak") String ak
            , @Query("location") String location
            , @Query("output") String output
            , @Query("pois") String pois
            , @Query("mcode") String mcode
    );
}
