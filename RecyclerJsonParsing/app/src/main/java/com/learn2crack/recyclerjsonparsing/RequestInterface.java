package com.learn2crack.recyclerjsonparsing;

import retrofit2.Call;
import retrofit2.http.GET;

/**TODO: 8/14/16 4 create a retrofit instance
 * Our request URL is <a href="http://api.learn2crack.com/android/jsonandroid"></a>,
 * where http://api.learn2crack.com is base url and android/jsonandroid is endpoint.
 *
 *
 */
public interface RequestInterface {

    /**
     * By using Call, the request is made Asynchronous so you need not worry about UI blocking or AsyncTask.
     * @return The JSON response received after making the request it is stored in JSONResponse object.
     */
    @GET("android/jsonandroid")
    Call<JSONResponse> getJSON();
}
