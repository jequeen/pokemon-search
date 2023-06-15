package com.example.pokemonsearch.screens.search

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.pokemonsearch.R
import com.example.pokemonsearch.data.model.Pokemon
import com.example.pokemonsearch.ui.theme.PokemonSearchTheme

@Composable
fun PokemonSearchRoute(
    viewModel: PokemonSearchViewModel = hiltViewModel(),
    onPokemonSelected: (Pokemon) -> Unit
) {

    PokemonSearchScreen(
        pokemonSearchScreenState = viewModel.searchScreenState.value,
        onPokemonSelected = {
            onPokemonSelected(it)
            viewModel.clearSelectedPokemon()
        },
        onSearchClick = viewModel::searchPokemonByName,
        selectedPokemon = viewModel.selectedPokemon.value
    )
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun PokemonSearchScreen(
    pokemonSearchScreenState: PokemonSearchScreenState,
    onPokemonSelected: (Pokemon) -> Unit,
    onSearchClick: (String) -> Unit,
    selectedPokemon: Pokemon?
) {
    val pokemonNameState = remember { mutableStateOf("") }

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        // Display the Pokemon logo
        PokemonLogo()

        // TextField for entering the Pokemon name
        TextField(
            value = pokemonNameState.value,
            onValueChange = { pokemonNameState.value = it },
            label = { Text(stringResource(R.string.enter_pokemon_name)) },
            modifier = Modifier
                .fillMaxWidth()
                .padding(horizontal = dimensionResource(R.dimen.m_padding))
        )

        // Button for triggering the search
        Button(
            onClick = {
                // Call the onSearchClick callback with the entered name
                onSearchClick(pokemonNameState.value)
            },
            modifier = Modifier.padding(top = dimensionResource(R.dimen.l_padding)),
            enabled = pokemonSearchScreenState != PokemonSearchScreenState.Loading // Disable the button if already loading
        ) {
            // Display loading indicator or search text based on isLoading state
            if (pokemonSearchScreenState == PokemonSearchScreenState.Loading) {
                CircularProgressIndicator(
                    modifier = Modifier
                        .size(dimensionResource(R.dimen.m_padding))
                        .align(Alignment.CenterVertically)
                )
            } else {
                Text(stringResource(R.string.search))
            }
        }
        if(pokemonSearchScreenState == PokemonSearchScreenState.Error) {
            Text(stringResource(R.string.error_occurred))
        }
    }

    // Observe the Pokemon data from the selectedPokemon state
    if (selectedPokemon != null) {
        LaunchedEffect(selectedPokemon) {
            onPokemonSelected(selectedPokemon)
        }
    }
}




@Composable
private fun PokemonLogo() {
    Image(
        painter = painterResource(id = R.drawable.pokemon_logo),
        contentDescription = stringResource(R.string.pokemon_logo),
        modifier = Modifier
            .fillMaxWidth()
            .padding(vertical = dimensionResource(R.dimen.xl_padding))
    )
}

@Composable
@Preview(showBackground = true)
fun PokemonSearchScreenPreview() {
    PokemonSearchTheme {
        PokemonSearchScreen(PokemonSearchScreenState.Ready, {} , {}, null)
    }
}
