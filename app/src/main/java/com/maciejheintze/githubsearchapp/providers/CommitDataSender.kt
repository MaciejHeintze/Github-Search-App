package com.maciejheintze.githubsearchapp.providers

import android.content.Context
import android.content.Intent

interface CommitDataSender {
    fun sendCommitData(commitData: String, context: Context)
}

class CommitDataSenderImpl : CommitDataSender {
    override fun sendCommitData(commitData: String, context: Context) {
        val intent = Intent(Intent.ACTION_SEND)
        intent.type = "text/plain"
        intent.putExtra(Intent.EXTRA_SUBJECT, "Commit Data")
        intent.putExtra(Intent.EXTRA_TEXT, commitData)
        intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
        context.startActivity(Intent.createChooser(intent, "Send Commit Data"))
    }
}