package com.example.myapplication.view.creatures

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.viewmodel.CreaturesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreaturesListFragment : Fragment() {
    private val creaturesViewModel: CreaturesViewModel by viewModels()
    private lateinit var adapter: CreaturesListAdapter


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.creatures_list_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)

        adapter = CreaturesListAdapter(mutableListOf())

        recyclerView.layoutManager = LinearLayoutManager(requireContext())
        recyclerView.adapter = adapter
        recyclerView.setHasFixedSize(true)

        creaturesViewModel.creatures.observe(viewLifecycleOwner) {
            adapter.updateList(it)
        }
    }
}