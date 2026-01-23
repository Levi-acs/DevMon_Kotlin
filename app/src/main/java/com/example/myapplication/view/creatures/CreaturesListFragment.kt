package com.example.myapplication.view.creatures

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.viewmodel.CreaturesViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class CreaturesListFragment : Fragment() {
    private val creaturesViewModel: CreaturesViewModel by viewModels()
    // forçando a tela atualizar a lista de criatura após aparecer a sua primeira criatura
    private var adapter: CreaturesListAdapter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        return inflater.inflate(R.layout.creatures_list_fragment, container, false)
    }



    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val recyclerView = view.findViewById<RecyclerView>(R.id.recyclerView)


        recyclerView.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false)

        creaturesViewModel.creatures.observe(viewLifecycleOwner) { creatures ->
            recyclerView.adapter = CreaturesListAdapter(creatures) { creature ->
                if (!creature.isKnown) {
                    return@CreaturesListAdapter
                }

                val action = CreaturesListFragmentDirections.creatureViewAction(creature.number)

                findNavController().navigate(action)
            }
        }
    }

    override fun onResume() {
        super.onResume()
        // Força atualizar os dados quando o fragment volta a ficar visível
        creaturesViewModel.refreshCreatures()
    }
}
