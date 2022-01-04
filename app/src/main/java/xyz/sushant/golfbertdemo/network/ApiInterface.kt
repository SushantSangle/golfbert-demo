package xyz.sushant.golfbertdemo.network

import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Path
import retrofit2.http.Query
import xyz.sushant.golfbertdemo.models.course.Course
import xyz.sushant.golfbertdemo.models.course.CoursesList
import xyz.sushant.golfbertdemo.models.hole.Hole
import xyz.sushant.golfbertdemo.models.hole.HolesList
import xyz.sushant.golfbertdemo.models.polygon.PolygonList

interface ApiInterface {
    @GET("/courses/")
    suspend fun getAllAvailableCourses() : Response<CoursesList>

    @GET("/course/{id}/")
    suspend fun getCourseDetails(@Path("id") courseId: Int) : Response<Course>

    @GET("/holes/")
    suspend fun getHolesForCourse(@Query("courseId") courseId: Int) : Response<HolesList>

    @GET("/holes/{id}")
    suspend fun getHoleDetails(@Path("id") holeId: Int) : Response<Hole>

    @GET("/v1/holes/{id}/polygons")
    suspend fun getHolePolygons(@Path("id") holeId: Int) : Response<PolygonList>
}