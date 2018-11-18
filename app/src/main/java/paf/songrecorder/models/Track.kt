package paf.songrecorder.models

import java.io.File
import java.io.Serializable


data class Track(
        val title: String,
        val date: String?,
        val rootFolder: String?,
        val audioFile: File): Serializable