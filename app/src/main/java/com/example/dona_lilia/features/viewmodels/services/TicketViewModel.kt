import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.dona_lilia.features.models.Tickets
import com.example.dona_lilia.features.services.FirebaseService
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class TicketViewModel(
    private val firebaseService: FirebaseService = FirebaseService()
) : ViewModel() {
    private val _tickets = MutableStateFlow<List<Tickets>>(emptyList())
    val tickets: StateFlow<List<Tickets>> get() = _tickets

    init {
        fetchTickets()
    }

    private fun fetchTickets() {
        viewModelScope.launch {
            firebaseService.fetchTickets().collect { fetchedTickets ->
                _tickets.value = fetchedTickets
            }
        }
    }
}
