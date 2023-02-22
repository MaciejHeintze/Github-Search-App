package com.maciejheintze.githubsearchapp.presentation.screen.search

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Send
import androidx.compose.runtime.*
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.maciejheintze.githubsearchapp.db.model.CommitDetail
import com.maciejheintze.githubsearchapp.presentation.MainViewModel

@Composable
fun SearchScreen(viewModel: MainViewModel) {
    val ownerFieldState = remember { mutableStateOf(TextFieldValue()) }
    val repoFieldState = remember { mutableStateOf(TextFieldValue()) }
    val searchEnabled =
        ownerFieldState.value.text.isNotEmpty() && repoFieldState.value.text.isNotEmpty()

    Scaffold(
        topBar = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 16.dp, vertical = 16.dp)
            ) {
                OutlinedTextField(
                    value = ownerFieldState.value,
                    onValueChange = { ownerFieldState.value = it },
                    label = { Text("Repository owner") },
                    modifier = Modifier.weight(1f),
                    singleLine = true
                )
                Spacer(modifier = Modifier.width(16.dp))
                OutlinedTextField(
                    value = repoFieldState.value,
                    onValueChange = { repoFieldState.value = it },
                    label = { Text("Repository name") },
                    modifier = Modifier.weight(1f),
                    singleLine = true
                )
            }
        }
    ) { innerPadding ->
        Column(modifier = Modifier.padding(innerPadding)) {
            Button(
                onClick = {
                    viewModel.fetchRepositoryIdAndCommits(
                        owner = ownerFieldState.value.text,
                        repo = repoFieldState.value.text,
                    )
                },
                modifier = Modifier
                    .padding(top = 36.dp, start = 36.dp, end = 36.dp)
                    .fillMaxWidth()
                    .height(56.dp)
                    .clip(RoundedCornerShape(28.dp)),
                enabled = searchEnabled,
                colors = ButtonDefaults.buttonColors(
                    backgroundColor = MaterialTheme.colors.primary
                )
            ) {
                Icon(Icons.Default.Search, contentDescription = "Search")
                Text(
                    text = "Search",
                    style = MaterialTheme.typography.button,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
            Spacer(modifier = Modifier.height(16.dp))
            RepositoryContent(viewModel = viewModel)
        }
    }
}

@Composable
fun RepositoryContent(viewModel: MainViewModel) {
    var selectedCount by remember { mutableStateOf(0) }

    val onCheckedChange: (Boolean) -> Unit = {
        selectedCount += if (it) 1 else -1
    }

    val context = LocalContext.current

    Box(modifier = Modifier.fillMaxSize()) {
        viewModel.repositoryId.observeAsState().value.let { githubRepositoryId ->
            viewModel.commits.observeAsState().value.let { commitsList ->
                viewModel.isLoading.observeAsState().value?.let {
                    if (!it.isLoading) {
                        Column(modifier = Modifier.fillMaxSize()) {
                            githubRepositoryId?.id?.let {
                                Text(
                                    "Repository ID: ${githubRepositoryId.id}",
                                    modifier = Modifier.padding(16.dp),
                                    fontSize = 26.sp,
                                    fontWeight = FontWeight.Bold,
                                )
                            }
                            if (!commitsList.isNullOrEmpty()) {
                                LazyColumn(modifier = Modifier.weight(1f)) {
                                    items(commitsList) { commit ->
                                        CommitItem(
                                            commit = commit,
                                            selectedCount = selectedCount,
                                            onCheckedChange = onCheckedChange,
                                        )
                                    }
                                }
                                if (selectedCount > 0) {
                                    Box(
                                        modifier = Modifier
                                            .fillMaxWidth()
                                            .padding(horizontal = 36.dp, vertical = 66.dp)
                                    ) {
                                        Button(
                                            onClick = {
                                                val selectedCommits =
                                                    commitsList.filter { commit ->
                                                        commit.selected
                                                    }
                                                viewModel.sendSelectedCommitData(
                                                    selectedCommits,
                                                    context
                                                )
                                            },
                                            modifier = Modifier
                                                .fillMaxWidth()
                                                .padding(16.dp)
                                        ) {
                                            Text("Send Commit")
                                            Spacer(modifier = Modifier.width(10.dp))
                                            Icon(Icons.Default.Send, contentDescription = "Send")
                                        }
                                    }
                                }
                            }
                        }
                    }
                }
            }
        }
    }
}

@Composable
fun CommitItem(
    commit: CommitDetail,
    selectedCount: Int,
    onCheckedChange: (Boolean) -> Unit,
) {
    var selected by remember { mutableStateOf(commit.selected) }

    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = 4.dp,
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Row(
            Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween
        ) {
            Column(Modifier.padding(16.dp)) {
                Text(
                    "Message: ${commit.message}",
                    color = Color.Black,
                    fontSize = 16.sp
                )
                Text(
                    "SHA Value: ${commit.shaValue}",
                    color = Color.Black,
                    fontSize = 14.sp
                )
                Text(
                    "Author Name: ${commit.authorName}",
                    color = Color.Black,
                    fontSize = 14.sp
                )
            }
            Checkbox(
                checked = selected,
                onCheckedChange = {
                    selected = it
                    commit.selected = it
                    onCheckedChange(it)
                },
                modifier = Modifier
                    .padding(end = 36.dp)
                    .align(Alignment.CenterVertically)
            )
        }
    }
}


