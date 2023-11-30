package com.example.flightapp.ui.fragments.home.search

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.flightapp.R
import com.example.flightapp.databinding.FragmentSearchFlightsBinding
import com.example.flightapp.ui.activities.MainActivity
import com.example.flightapp.ui.adapters.recyclerview.Flight
import com.example.flightapp.ui.adapters.recyclerview.FlightTicketAdapter

class SearchFlights : Fragment() {
    private lateinit var binding: FragmentSearchFlightsBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentSearchFlightsBinding.inflate(inflater)
        setAdapter()

        binding.arrowBack.setOnClickListener {
            findNavController().popBackStack()
        }
        return binding.root
    }

    private fun setAdapter() {
        val searchedDestinationFrom: String? = arguments?.getString("destinationFrom")
        val searchedDestinationTo: String? = arguments?.getString("destinationTo")
        val searchedDate: String? = arguments?.getString("date")
        val flightTickets = mutableListOf(
            Flight(
                txt1 = "LGA",
                txt2 = "DAD",
                from = "London",
                to = "New Jersey",
                hourTo = "10:00",
                hourFrom = "12:00",
                dateFrom = "2023-12-01",
                daterTo = "2023-12-01",
                companyName = "Airline A",
                companyImg = R.drawable.person_24,
                price = 250,
                duration = "2 hours 0 minutes"
            ),
            Flight(
                txt1 = "LGA",
                txt2 = "DAD",
                from = "London",
                to = "Baku",
                hourTo = "10:00",
                hourFrom = "12:00",
                dateFrom = "2023-12-01",
                daterTo = "2023-12-01",
                companyName = "Airline A",
                companyImg = R.drawable.person_24,
                price = 250,
                duration = "2 hours 0 minutes"
            ),
            Flight(
                txt1 = "LGA",
                txt2 = "DAD",
                from = "Istanbul",
                to = "Baku",
                hourTo = "10:00",
                hourFrom = "12:00",
                dateFrom = "2023-12-01",
                daterTo = "2023-12-01",
                companyName = "Airline A",
                companyImg = R.drawable.person_24,
                price = 250,
                duration = "2 hours 0 minutes"
            ),

            )

        val searchedFlights = flightTickets.filter { flight ->
            (searchedDestinationFrom.isNullOrBlank() || flight.from.toLowerCase() == searchedDestinationFrom.toLowerCase()) &&
                    (searchedDestinationTo.isNullOrBlank() || flight.to.toLowerCase() == searchedDestinationTo.toLowerCase()) &&
                    (searchedDate.isNullOrBlank() || flight.dateFrom == searchedDate)
        }

        if (searchedFlights.isNotEmpty()) {
            setupRecyclerView(searchedFlights)
        } else {
            showNoResultsMessage()
        }

    }

    private fun setupRecyclerView(searchedFlights: List<Flight>) {
        val adapter = FlightTicketAdapter(searchedFlights) {
            findNavController().navigate(R.id.action_searchFlights_to_bookingDetailsFragment)
        }
        binding.recyclerView.layoutManager = LinearLayoutManager(requireContext())
        binding.recyclerView.adapter = adapter
    }

    private fun showNoResultsMessage() {
        binding.recyclerView.visibility = View.GONE
        binding.textView7.visibility = View.VISIBLE
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        val activity = requireActivity() as? MainActivity
        activity?.setBottomNavigation(false)
    }


}