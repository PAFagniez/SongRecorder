package paf.songrecorder.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize
import java.io.File


@Parcelize
data class Track(
        val title: String,
        val date: String?,
        val rootFolder: String?,
        val audioFile: File) : Parcelable
