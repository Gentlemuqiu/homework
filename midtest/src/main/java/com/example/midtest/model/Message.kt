package com.example.midtest.model
import com.google.gson.annotations.SerializedName


data class Message(
    @SerializedName("comments")
    val comments: List<Comment>
) {
    data class Comment(
        @SerializedName("author")
        val author: String, // 每一天都在混水摸鱼
        @SerializedName("avatar")
        val avatar: String, // https://picx.zhimg.com/0ecf2216c2612b04592126adc16affa2_l.jpg?source=8673f162
        @SerializedName("content")
        val content: String, // 钱会让它变的好吃
        @SerializedName("id")
        val id: Int, // 556780
        @SerializedName("likes")
        val likes: Int, // 0
        @SerializedName("reply_to")
        val replyTo: ReplyTo,
        @SerializedName("time")
        val time: Int // 1413987020
    ) {
        data class ReplyTo(
            @SerializedName("author")
            val author: String, // 怒放的腋毛
            @SerializedName("content")
            val content: String, // 我每次都不假思索选了牛肉，然后就深深的后悔没有试过一次鸡肉，到下一次又情不自禁选了牛肉，周而复始循环往复-_-#
            @SerializedName("id")
            val id: Int, // 551969
            @SerializedName("status")
            val status: Int // 0
        )
    }
}