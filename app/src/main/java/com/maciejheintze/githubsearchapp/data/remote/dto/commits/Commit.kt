package com.maciejheintze.githubsearchapp.data.remote.dto.commits
import com.google.gson.annotations.SerializedName

data class Commit(
    var author: Author = Author(),
    @SerializedName("comment_count")
    var commentCount: Int = 0,
    var committer: Committer = Committer(),
    var message: String = "",
    var tree: Tree = Tree(),
    var url: String = "",
    var verification: Verification = Verification()
)