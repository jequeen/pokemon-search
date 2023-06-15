package com.example.pokemonsearch.screens.detail

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.pokemonsearch.R
import com.example.pokemonsearch.data.model.MoveDetails
import com.example.pokemonsearch.data.model.MoveType
import com.example.pokemonsearch.ui.theme.PokemonSearchTheme

@Composable
fun MoveDetailRoute(
    viewModel: MoveDetailViewModel = hiltViewModel(),
    onBack: () -> Unit
) {

    MoveDetailsScreen(
        selectedMove = viewModel.selectedMove.value,
        onBack = onBack
    )
}

@Composable
fun MoveDetailsScreen(
    selectedMove: MoveDetails?,
    onBack: () -> Unit
) {

    Column(
        Modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())) {
        // Back button
        Surface {
            IconButton(
                onClick = onBack,
                modifier = Modifier.padding(dimensionResource(R.dimen.s_padding))
            ) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = stringResource(R.string.back)
                )
            }
        }

        if (selectedMove != null) {
            // Display the move name
            Text(
                text = "${stringResource(R.string.name)} ${selectedMove.name ?: "N/A"}",
                modifier = Modifier.padding(dimensionResource(R.dimen.m_padding))
            )

            // Display the move accuracy
            Text(
                text = "${stringResource(R.string.accuracy)} ${selectedMove.accuracy ?: "N/A"}",
                modifier = Modifier.padding(dimensionResource(R.dimen.m_padding))
            )

            // Display the move PP
            Text(
                text = "${stringResource(R.string.pp)} ${selectedMove.pp ?: "N/A"}",
                modifier = Modifier.padding(dimensionResource(R.dimen.m_padding))
            )

            // Display the move power
            Text(
                text = "${stringResource(R.string.power)} ${selectedMove.power ?: "N/A"}",
                modifier = Modifier.padding(dimensionResource(R.dimen.m_padding))
            )

            // Display the move type
            Text(
                text = "${stringResource(R.string.type)} ${selectedMove.type?.name ?: "N/A"}",
                modifier = Modifier.padding(dimensionResource(R.dimen.m_padding))
            )
        } else {
            // Handle the case when the move data is null
            Text(text = stringResource(R.string.data_unavailable))
        }
    }
}

@Composable
@Preview(showBackground = true)
fun MoveDetailsScreenPreview() {
    // Create a sample move for preview
    val move = MoveDetails(
        name = "pound",
        accuracy = 100,
        pp = 35,
        power = 40,
        type = MoveType(name = "normal")
    )

    PokemonSearchTheme {
        // Preview the MoveDetailsScreen with the sample move
        MoveDetailsScreen(selectedMove = move, onBack = {})
    }
}
