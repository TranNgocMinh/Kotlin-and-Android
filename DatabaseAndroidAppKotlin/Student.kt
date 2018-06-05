package com.example.admin.mydatabasekotlinapp

/**
 * Created by Admin on 18/05/2018.
 */
class Student {
    var studentID: Int = 0
    var studentName: String? = null


    constructor(studentID: Int, studentName: String) {
        this.studentID = studentID
        this.studentName = studentName
    }

}
