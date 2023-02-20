package com.maciejheintze.githubsearchapp.presentation.screen.history

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.livedata.observeAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.maciejheintze.githubsearchapp.db.model.RepositoryEntity
import com.maciejheintze.githubsearchapp.presentation.MainViewModel

@Composable
fun HistoryScreen(viewModel: MainViewModel, modifier: Modifier = Modifier) {
    Box(modifier = Modifier.fillMaxSize()) {
        viewModel.repositoryDetailList.observeAsState().value?.let { repositoryDetailList ->
            viewModel.isLoading.observeAsState().value?.let {
                if (!it.isLoading) {
                    Column(modifier = Modifier.fillMaxSize()) {
                        if (repositoryDetailList.isNotEmpty()) {
                            LazyColumn(modifier = Modifier.weight(1f)) {
                                items(repositoryDetailList) { details ->
                                    RepositoryDetailItem(repositoryDetails = details)
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
fun RepositoryDetailItem(repositoryDetails: RepositoryEntity) {
    Card(
        shape = RoundedCornerShape(8.dp),
        elevation = 4.dp,
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
    ) {
        Column(Modifier.padding(16.dp)) {
            Text(
                "Owner: ${repositoryDetails.owner}",
                color = Color.Black,
                fontSize = 16.sp
            )
            Text(
                "Repository name: ${repositoryDetails.repo}",
                color = Color.Black,
                fontSize = 14.sp
            )
            Text(
                "Repository ID: ${repositoryDetails.id}",
                color = Color.Black,
                fontSize = 16.sp
            )
        }
    }
}