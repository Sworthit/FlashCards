package com.example.flashcards.data.entity

import io.realm.kotlin.ext.realmListOf
import io.realm.kotlin.types.RealmList
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import org.mongodb.kbson.ObjectId

class Subject: RealmObject {
    @PrimaryKey var _id: ObjectId = ObjectId()
    var flashCards: RealmList<FlashCard> = realmListOf()
    var name: String = ""
}