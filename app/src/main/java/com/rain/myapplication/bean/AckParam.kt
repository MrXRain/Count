package com.rain.myapplication.bean

import android.os.Parcelable
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

/**
 * @author rain
 */
@Parcelize
data class AckParam(
    @SerializedName("help") val help:String = "",
    @SerializedName("success") val flag:Boolean = false,
    @SerializedName("result") val result: Count? = null
) : Parcelable

@Parcelize
data class Count(
    @SerializedName("resource_id") val id:String = "",
    @SerializedName("fields") val fields:List<Filed>? = null,
    @SerializedName("records") val records:MutableList<Record>? = null,
    @SerializedName("_links") val link: Link? = null,
    @SerializedName("limit") val limit:Int = 0,
    @SerializedName("total") val total:Int = 0
): Parcelable

@Parcelize
data class Filed(
    @SerializedName("type") val type:String = "",
    @SerializedName("id") val id:String = ""
): Parcelable

@Parcelize
data class Record(
    @SerializedName("volume_of_mobile_data") val data:Float = .0f,
    @SerializedName("quarter") val quarter:String = "",
    @SerializedName("_id") val id:Int = -1
): Parcelable

@Parcelize
data class Link(
    @SerializedName("start") val start:String = "",
    @SerializedName("next") val next:String = ""
): Parcelable

//{
//    "help": "https://data.gov.sg/api/3/action/help_show?name=datastore_search",
//    "success": true,
//    "result": {
//    "resource_id": "a807b7ab-6cad-4aa6-87d0-e283a7353a0f",
//    "fields": [
//    {
//        "type": "int4",
//        "id": "_id"
//    },
//    {
//        "type": "text",
//        "id": "quarter"
//    },
//    {
//        "type": "numeric",
//        "id": "volume_of_mobile_data"
//    }
//    ],
//    "records": [
//    {
//        "volume_of_mobile_data": "0.000384",
//        "quarter": "2004-Q3",
//        "_id": 1
//    },
//    {
//        "volume_of_mobile_data": "0.000543",
//        "quarter": "2004-Q4",
//        "_id": 2
//    }
//    ],
//    "_links": {
//        "start": "/api/action/datastore_search",
//        "next": "/api/action/datastore_search?offset=20"
//    },
//    "limit": 20,
//    "total": 59
//}
//}