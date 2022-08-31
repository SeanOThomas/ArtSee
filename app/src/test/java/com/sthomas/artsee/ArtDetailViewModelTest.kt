package com.sthomas.artsee

import androidx.lifecycle.SavedStateHandle
import com.sthomas.artsee.domain.model.Art
import com.sthomas.artsee.domain.repository.ArtRepository
import com.sthomas.artsee.presentation.art_detail.ArtDetailState
import com.sthomas.artsee.presentation.art_detail.ArtDetailViewModel
import com.sthomas.artsee.repostiory.FakeRepository
import com.sthomas.artsee.ui.Args
import kotlinx.coroutines.*
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.junit.After
import org.junit.Before
import org.junit.Test

@OptIn(ExperimentalCoroutinesApi::class)
class ArtDetailViewModelTest {

    private val mainThreadSurrogate = newSingleThreadContext("UI thread")

    private val targetArtId = "123"
    private val initialState = ArtDetailState(
        art = null,
        isLoading = true,
        isSaving = false,
        isSaved = false
    )

    private lateinit var storageRepository: ArtRepository
    private lateinit var remoteRepository: ArtRepository
    private lateinit var artDetailViewModel: ArtDetailViewModel

    @Before
    fun setUp() {
        Dispatchers.setMain(mainThreadSurrogate)

        storageRepository = FakeRepository()
        remoteRepository = FakeRepository()
    }

    @After
    fun tearDown() {
        Dispatchers.resetMain() // reset the main dispatcher to the original Main dispatcher
        mainThreadSurrogate.close()
    }


    @Test
    fun `Art is saved if found in storage`() = runBlocking {
        (storageRepository as FakeRepository).addShuffledArtList(includeTargetArt = true)

        artDetailViewModel = ArtDetailViewModel(
            remoteRepository,
            storageRepository,
            initialState,
            SavedStateHandle(
                mapOf(
                    Args.artId to targetArtId
                )
            ),
        )
        // hack to wait until coroutine has completed in view model init block
        delay(50)

        with(artDetailViewModel.state) {
            assert(art != null)
            assert(art!!.id == targetArtId)
            assert(isSaved)
        }
    }

    @Test
    fun `Get art from remote if storage error`() = runBlocking {
        (storageRepository as FakeRepository).apply {
            addShuffledArtList(includeTargetArt = true)
            isError = true
        }
        (remoteRepository as FakeRepository).addShuffledArtList(includeTargetArt = true)


        artDetailViewModel = ArtDetailViewModel(
            remoteRepository,
            storageRepository,
            initialState,
            SavedStateHandle(
                mapOf(
                    Args.artId to targetArtId
                )
            ),
        )
        // hack to wait until coroutine has completed in view model init block
        delay(50)

        with(artDetailViewModel.state) {
            assert(art != null)
            assert(art!!.id == targetArtId)
            assert(isSaved.not())
        }
    }

    @Test
    fun `Get art from remote if not saved`() = runBlocking {
        (remoteRepository as FakeRepository).addShuffledArtList(includeTargetArt = true)

        artDetailViewModel = ArtDetailViewModel(
            remoteRepository,
            storageRepository,
            initialState,
            SavedStateHandle(
                mapOf(
                    Args.artId to targetArtId
                )
            ),
        )
        // hack to wait until coroutine has completed in view model init block
        delay(50)

        with(artDetailViewModel.state) {
            assert(art != null)
            assert(art!!.id == targetArtId)
            assert(isSaved.not())
        }
    }

    @Test
    fun `Get error if there's a remote error`() = runBlocking {
        (remoteRepository as FakeRepository).apply {
            addShuffledArtList(includeTargetArt = true)
            (remoteRepository as FakeRepository).isError = true
        }

        artDetailViewModel = ArtDetailViewModel(
            remoteRepository,
            storageRepository,
            initialState,
            SavedStateHandle(
                mapOf(
                    Args.artId to targetArtId
                )
            ),
        )
        // hack to wait until coroutine has completed in view model init block
        delay(50)

        with(artDetailViewModel.state) {
            assert(art == null)
            assert(error != null)
        }
    }

    private fun FakeRepository.addShuffledArtList(includeTargetArt: Boolean = true) {
        val artList = mutableListOf(
            getFakeArt(),
            getFakeArt(),
            getFakeArt()
        )

        if (includeTargetArt) {
            artList.add(getFakeArt(targetArtId))
        }

        this.artList.addAll(
            artList.shuffled()
        )
    }

    private fun getFakeArt(artId: String = generateRandomId()) = Art(
        artId,
        "NA",
        "NA",
        "NA",
        "NA"
    )

    private fun generateRandomId() : String = (0..987654321).random().toString()
}