package com.example.midtest.model

import com.google.gson.annotations.SerializedName

data class latest(
    @SerializedName("date")
    val date: String, // 20230425
    @SerializedName("stories")
    val stories: List<Story>,
    @SerializedName("top_stories")
    val topStories: List<TopStory>
) {
    data class Story(
        @SerializedName("ga_prefix")
        val gaPrefix: String, // 042507
        @SerializedName("hint")
        val hint: String, // 法小喵 · 4 分钟阅读
        @SerializedName("id")
        val id: String, // 9760754
        @SerializedName("image_hue")
        val imageHue: String, // 0xb39168
        @SerializedName("images")
        val images: List<String>,
        @SerializedName("title")
        val title: String, // 昆虫为什么要进化出蛹这种不利于生存的形态？
        @SerializedName("type")
        val type: Int, // 0
        @SerializedName("url")
        val url: String // https://daily.zhihu.com/story/9760754
    )

    data class TopStory(
        @SerializedName("ga_prefix")
        val gaPrefix: String, // 042107
        @SerializedName("hint")
        val hint: String, // 作者 / 今夕何夕
        @SerializedName("id")
        val id: String, // 9760718
        @SerializedName("image")
        val image: String, // https://picx.zhimg.com/v2-5e21ac17e9c4266261e28282433de0b2.jpg?source=8673f162
        @SerializedName("image_hue")
        val imageHue: String, // 0x935755
        @SerializedName("title")
        val title: String, // 请问在中国历史上，洛阳和长安各自作为首都的利弊有哪些？
        @SerializedName("type")
        val type: Int, // 0
        @SerializedName("url")
        val url: String // https://daily.zhihu.com/story/9760718
    )
}
