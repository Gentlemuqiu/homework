package com.example.midtest.model
import com.google.gson.annotations.SerializedName


data class before(
    @SerializedName("date")
    val date: String, // 20230424
    @SerializedName("stories")
    val stories: List<Story>
) {
    data class Story(
        @SerializedName("ga_prefix")
        val gaPrefix: String, // 042407
        @SerializedName("hint")
        val hint: String, // 思思安 · 8 分钟阅读
        @SerializedName("id")
        val id: String, // 9759918
        @SerializedName("image_hue")
        val imageHue: String, // 0x2e3022
        @SerializedName("images")
        val images: List<String>,
        @SerializedName("title")
        val title: String, // 《甄嬛传》里的淳儿到底是真的单纯还是有心计藏得深？
        @SerializedName("type")
        val type: Int, // 0
        @SerializedName("url")
        val url: String // https://daily.zhihu.com/story/9759918
    )
}