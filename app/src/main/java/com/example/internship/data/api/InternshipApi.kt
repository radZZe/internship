package com.example.internship.data.api

import com.example.internship.models.Internship
import retrofit2.http.GET

interface InternshipApi {
    @GET("internships.json")
    suspend fun fetchInternships():List<Internship>

}