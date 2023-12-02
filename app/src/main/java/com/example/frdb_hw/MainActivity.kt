package com.example.frdb_hw

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.frdb_hw.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private val binding: ActivityMainBinding by lazy { ActivityMainBinding.inflate(layoutInflater) }
    private val adapter: SongAdapter = SongAdapter()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(binding.root)


        binding.songsRecycler.layoutManager = LinearLayoutManager(this)
        binding.songsRecycler.adapter = adapter
        val playlistDao = PlaylistDao()

        binding.save.setOnClickListener {
            val playlist = getPlaylist()
            if (!binding.updateName.text.isNullOrEmpty()) {
                playlist.name = binding.updateName.text.toString()
            }
            playlistDao.saveData(playlist)
            binding.updateName.text.clear()
        }
        binding.get.setOnClickListener {
            playlistDao.getData()
        }

        playlistDao.getDataLiveData.observe(this) { playlist ->
            load(playlist)
        }

        playlistDao.updateLiveData.observe(this) {playlist ->
            load(playlist)
        }


    }

    private fun load(playlist: Playlist?) {
        playlist?.let {
            binding.playlistName.text = it.name
            binding.language.text = it.language
            binding.isPrivate.text = it.private.toString()
            binding.user.text = it.user?.nickname
            adapter.submitList(playlist.songList)
        }
    }
}