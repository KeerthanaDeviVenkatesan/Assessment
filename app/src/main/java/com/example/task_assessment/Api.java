package com.example.task_assessment;

import com.example.task_assessment.model.RandomDataResponse;



import retrofit2.Call;
import retrofit2.http.GET;

public interface Api {
    @GET("v1/1/categories.php")
    Call<RandomDataResponse> randomDataCall();


}
