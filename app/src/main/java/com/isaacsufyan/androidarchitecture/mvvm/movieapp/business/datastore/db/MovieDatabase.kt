package com.isaacsufyan.androidarchitecture.mvvm.movieapp.business.datastore.db

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.isaacsufyan.androidarchitecture.mvvm.movieapp.business.model.Movie

@Database(entities = [Movie::class], version = 1)
@TypeConverters(GenreIdConverter::class)
abstract class MovieDatabase : RoomDatabase() {
    abstract fun movieDao(): MovieDao

}