package com.brittlepins.brittlepins.main.ui.boards

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.brittlepins.brittlepins.R

class BoardsFragment : Fragment() {

    private lateinit var boardsViewModel: BoardsViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        boardsViewModel =
            ViewModelProviders.of(this).get(BoardsViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_boards, container, false)
        val textView: TextView = root.findViewById(R.id.text_boards)
        boardsViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}