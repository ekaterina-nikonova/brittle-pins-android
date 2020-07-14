package com.brittlepins.brittlepins.main.ui.public

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.brittlepins.brittlepins.R

class PublicFragment : Fragment() {

    private lateinit var publicViewModel: PublicViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        publicViewModel =
            ViewModelProviders.of(this).get(PublicViewModel::class.java)
        val root = inflater.inflate(R.layout.fragment_public, container, false)
        val textView: TextView = root.findViewById(R.id.text_public)
        publicViewModel.text.observe(viewLifecycleOwner, Observer {
            textView.text = it
        })
        return root
    }
}
