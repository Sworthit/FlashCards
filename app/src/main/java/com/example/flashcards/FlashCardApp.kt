package com.example.flashcards

import android.app.Application
import com.example.flashcards.data.entity.FlashCard
import com.example.flashcards.data.entity.Subject
import dagger.hilt.android.HiltAndroidApp
import io.realm.kotlin.Realm
import io.realm.kotlin.RealmConfiguration

@HiltAndroidApp
class FlashCardApp: Application() {
    companion object {
        lateinit var realm: Realm
    }

    override fun onCreate() {
        super.onCreate()
        realm = Realm.open(
            configuration = RealmConfiguration.create(
                schema = setOf(
                    Subject::class,
                    FlashCard::class
                )
            )
        )
    }
}