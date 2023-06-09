package com.example.pokemonsearch.screens.list

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.aspectRatio
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.dimensionResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.rememberAsyncImagePainter
import com.example.pokemonsearch.R
import com.example.pokemonsearch.data.model.Move
import com.example.pokemonsearch.data.model.MoveHolder
import com.example.pokemonsearch.data.model.Pokemon
import com.example.pokemonsearch.data.model.Sprite
import com.example.pokemonsearch.ui.theme.PokemonSearchTheme
import java.util.Locale

@Composable
fun PokemonInfoListRoute(
    viewModel: PokemonInfoListViewModel = hiltViewModel(),
    onBack: () -> Unit,
    onMoveAvailable: (String) -> Unit
) {

    PokemonInfoListScreen(
        selectedPokemon = viewModel.selectedPokemon.value,
        selectedMove = viewModel.moveSelected.value,
        onBack = onBack,
        onMoveClick = viewModel::searchMoveByName,
        onMoveAvailable = {
            onMoveAvailable(it)
            viewModel.clearSelectedMove()
        }
    )
}

@Composable
fun PokemonInfoListScreen(
    selectedPokemon: Pokemon?,
    selectedMove: String?,
    onBack: () -> Unit,
    onMoveClick: (String) -> Unit,
    onMoveAvailable: (String) -> Unit
) {
    LazyColumn(
        Modifier.fillMaxWidth()
    ) {
        item {
            // Back button
            Surface {
                IconButton(
                    onClick = onBack,
                    modifier = Modifier.padding(8.dp)
                ) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = stringResource(R.string.back)
                    )
                }
            }
            // Display the sprite using coil
            Image(
                painter = rememberAsyncImagePainter(selectedPokemon?.sprites?.frontDefault),
                contentDescription = stringResource(R.string.pokemon_sprite),
                modifier = Modifier
                    .size(120.dp)
                    .aspectRatio(1f)
                    .padding(bottom = dimensionResource(R.dimen.m_padding))
            )

            // Display the details
            Column(Modifier.padding(horizontal = dimensionResource(R.dimen.s_padding))) {
                DetailItem(
                    label = stringResource(R.string.name),
                    value = selectedPokemon?.name ?: "N/A"
                )
                DetailItem(
                    label = stringResource(R.string.height),
                    value = selectedPokemon?.height?.toString() ?: "N/A"
                )
                DetailItem(
                    label = stringResource(R.string.weight),
                    value = selectedPokemon?.weight?.toString() ?: "N/A"
                )

                // Add padding between weight and move list
                Spacer(modifier = Modifier.height(dimensionResource(R.dimen.m_padding)))

                Text(
                    text = stringResource(R.string.move_list),
                    modifier = Modifier.padding(bottom = dimensionResource(R.dimen.s_padding))
                )
            }
        }
        // Display the moves list inside a LazyColumn
        items(selectedPokemon?.moves.orEmpty()) { moveHolder ->
            Button(
                onClick = {
                    onMoveClick(moveHolder.move.name)
                },
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(bottom = dimensionResource(R.dimen.xs_padding))
            ) {
                Text(text = moveHolder.move.name.capitalize(Locale.ROOT))
            }
        }
    }
    if (selectedMove != null) {
        LaunchedEffect(key1 = selectedMove) {
            onMoveAvailable(selectedMove)
        }
    }
}

@Composable
private fun DetailItem(label: String, value: String) {
    Text(
        text = "$label ${value.capitalize(Locale.ROOT)}",
        modifier = Modifier.fillMaxWidth()
    )
}

@Preview(showBackground = true)
@Composable
fun PokemonInfoListScreenPreview() {
    val pokemon = Pokemon(
        name = "Charizard",
        weight = 100,
        height = 50,
        moves = listOf(
            MoveHolder(Move("Move 1")),
            MoveHolder(Move("Move 2")),
            MoveHolder(Move("Move 3")),
            MoveHolder(Move("Move 4")),
            MoveHolder(Move("Move 5")),
            MoveHolder(Move("Move 6")),
            MoveHolder(Move("Move 7")),
            MoveHolder(Move("Move 8")),
            MoveHolder(Move("Move 9")),

            ),
        sprites = Sprite(frontDefault = "https://raw.githubusercontent.com/PokeAPI/sprites/master/sprites/pokemon/6.png")
    )
    PokemonSearchTheme {
        PokemonInfoListScreen(selectedPokemon = pokemon, null, {}, {}, {})
    }
}
