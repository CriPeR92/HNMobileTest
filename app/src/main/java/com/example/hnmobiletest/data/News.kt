package com.example.hnmobiletest.data

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize
import java.text.SimpleDateFormat
import java.util.*

@Parcelize
@Entity(tableName = "news_table")
data class News(
    @SerializedName("id") @PrimaryKey(autoGenerate = true) val id: Int,
    @SerializedName("created_at") val created_at: String?,
    @SerializedName("title") val title: String?,
    @SerializedName("url") val url: String?,
    @SerializedName("author") val author: String?,
    @SerializedName("story_title") val story_title: String?,
    @SerializedName("story_url") val story_url: String?,
    @SerializedName("objectID") val objectID: String?
): Parcelable {
    fun  getDateFormatted() : String {
        val parser = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss", Locale.ENGLISH)
        val formatter = SimpleDateFormat("HH:mm", Locale.ENGLISH)
        val output = formatter.format(parser.parse(created_at))

        return "- $output"
    }

    fun setTitle() : String? {
        return if (title == null) {
            story_title
        } else {
            title
        }
    }
}
