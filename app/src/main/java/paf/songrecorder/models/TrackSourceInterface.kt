package paf.recylclerviewtest.data

import paf.songrecorder.models.Track

interface TrackSourceInterface {

    val listOfData: List<Track>
    fun createNewListItem(): Track
}