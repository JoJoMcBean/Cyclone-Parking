package com.example.demo.Lot0;

        import org.springframework.data.jpa.repository.JpaRepository;
        import org.springframework.data.jpa.repository.Query;
        import org.springframework.stereotype.Repository;

        import java.util.List;

@Repository
public interface Lot_Zero_Repository extends JpaRepository<Lot_Zero, Integer> {

    @Query(value = "SELECT spotnum FROM Lot_Zero WHERE isfilled = true")
    List<Integer> getFilled();

    @Query(value = "SELECT spotnum FROM Lot_Zero WHERE isfilled = false")
    List<Integer> getEmpty();



}
