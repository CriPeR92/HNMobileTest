package com.example.hnmobiletest.models

import com.google.gson.annotations.SerializedName
import java.util.*

class NewsResponse(@SerializedName("hits") var hits: ArrayList<Hits>,
                   @SerializedName("nbHits") var nbHits: Int?,
                   @SerializedName("page") var page: Int?,
                   @SerializedName("nbPages") var nbPages: Int?,
                   @SerializedName("hitsPerPage") var hitsPerPage: Int?,
                   @SerializedName("exhaustiveNbHits") var exhaustiveNbHits: Boolean?,
                   @SerializedName("query") var query: String?,
                   @SerializedName("params") var params: String?,
                   @SerializedName("processingTimeMS") var processingTimeMS: Int?)

class Hits(
        @SerializedName("created_at") var created_at: String?,
        @SerializedName("title") var title: String?,
        @SerializedName("url") var url: String?,
        @SerializedName("author") var author: String?,
        @SerializedName("points") var points: Any?,
        @SerializedName("story_text") var story_text: Any?,
        @SerializedName("comment_text") var comment_text: Any?,
        @SerializedName("num_comments") var num_comments: Any?,
        @SerializedName("story_id") var story_id: Any?,
        @SerializedName("story_title") var story_title: String?,
        @SerializedName("story_url") var story_url: String?,
        @SerializedName("parent_id") var parent_id: Any?,
        @SerializedName("created_at_i") var created_at_i: Any?,
        @SerializedName("_tags") var _tags: Any?,
        @SerializedName("objectID") var objectID: String?,
        @SerializedName("_highlightResult") var _highlightResult: Any?,
)