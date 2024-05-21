package NationalCountries.services.UIRepresentationsServices;

import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class PaginationService {

    public List<List<Object>> paginate(List<Object> inputList, int pageNumber, int pageSize) {
        int totalItems = inputList.size();

        List<List<Object>> paginatedList = new ArrayList<>(pageNumber);

        for (int i = 0; i < pageNumber; i++) {
            int start = i * pageSize;
            int end = Math.min(start + pageSize, totalItems);
            if (start < totalItems) {
                paginatedList.add(new ArrayList<>(inputList.subList(start, end)));
            } else {
                paginatedList.add(new ArrayList<>());
            }
        }

        return paginatedList;
    }
}
