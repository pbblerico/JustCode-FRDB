package com.example.frdb_hw

import java.nio.file.attribute.UserPrincipal
import java.util.Date

data class Playlist(
    var name: String? = null,
    val user: User? = null,
    val songList: List<Song>? = null,
    val private: Boolean? = null,
    val language: String? = null,
) {
//    fun getLanguage(): Language = Language.values().firstOrNull {
//        it.name == language
//    } ?: Language.EN

    override fun toString(): String {
        return "name: $name, user: ${user.toString()}, private: $private, language: $language}, songs: $songList"
    }
}

fun getPlaylist() = Playlist(
        "my playlist",
        User("cool user", "M", "pretty nickname"),
        listOf(
            Song("first song", "me", "album 1"),
            Song("good song", "me", "album 2"),
            Song("bad song", "he", "album 3"),
            Song("one song", "idk", "album 5"),
            Song("not first song", "someone", "album 4"),
            Song("song to song", "you", "album 2"),
        ),
        true,
        "EN"
    )

enum class Language {
    RU, EN, KZ
}

data class User(
    val name: String? = null,
    val gender: String? = null,
    val nickname: String? = null,
) {
    fun getUserGender(): UserGender = UserGender.values().firstOrNull {
        it.name == gender
    } ?: UserGender.M

    override fun toString(): String {
        return "name: $name, gender: ${getUserGender()}, nickname: $nickname"
    }
}

data class Song(
    val name: String? = null,
    val author: String? = null,
    val album: String? = null,
)

enum class UserGender {
    M, W
}

class PlaylistDao: FRDBWrapper<Playlist>() {
    override fun getTableName(): String = "Playlist"

    override fun getClassType(): Class<Playlist> = Playlist::class.java

}
