package com.example.profnotes.data.database

import androidx.room.TypeConverter
import com.example.profnotes.domain.model.Message
import com.example.profnotes.domain.model.RichText
import com.example.profnotes.domain.model.User
import com.squareup.moshi.JsonAdapter
import com.squareup.moshi.Moshi

class Converter {

    private val moshi = Moshi.Builder().build()

    private val richTextAdapter: JsonAdapter<RichText> =
        moshi.adapter(RichText::class.java)

    private val richTextArrayAdapter: JsonAdapter<Array<RichText>> =
        moshi.adapter(Array<RichText>::class.java)

    private val messageAdapter: JsonAdapter<Message> =
        moshi.adapter(Message::class.java)

    private val messageArrayAdapter: JsonAdapter<Array<Message>> =
        moshi.adapter(Array<Message>::class.java)

    private val userAdapter: JsonAdapter<User> =
        moshi.adapter(User::class.java)

    @TypeConverter
    fun richTextToJson(richText: RichText): String =
        richTextAdapter.toJson(richText)

    @TypeConverter
    fun jsonToRichText(json: String): RichText =
        richTextAdapter.fromJson(json)!!

    @TypeConverter
    fun richTextArrayToJson(richTextArray: Array<RichText>): String =
        richTextArrayAdapter.toJson(richTextArray)

    @TypeConverter
    fun jsonToRichTextArray(json: String): Array<RichText> =
        richTextArrayAdapter.fromJson(json)!!

    @TypeConverter
    fun messageToJson(message: Message): String =
        messageAdapter.toJson(message)

    @TypeConverter
    fun jsonToMessage(json: String): Message =
        messageAdapter.fromJson(json)!!

    @TypeConverter
    fun messageArrayToJson(messages: Array<Message>): String =
        messageArrayAdapter.toJson(messages)

    @TypeConverter
    fun jsonToMessageArray(json: String): Array<Message> =
        messageArrayAdapter.fromJson(json)!!

    @TypeConverter
    fun userToJson(user: User): String =
        userAdapter.toJson(user)

    @TypeConverter
    fun jsonToUser(json: String): User =
        userAdapter.fromJson(json)!!

    @TypeConverter
    fun arrayStringToString(array: Array<String>): String {
        var str = ""
        for (s in array) {
            str += "$s,"
        }
        return str.dropLast(1)
    }

    @TypeConverter
    fun stringToArrayString(str: String): Array<String> =
        str.split(",").toTypedArray()
}