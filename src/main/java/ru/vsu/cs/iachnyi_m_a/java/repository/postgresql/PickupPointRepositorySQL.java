package ru.vsu.cs.iachnyi_m_a.java.repository.postgresql;

import ru.vsu.cs.iachnyi_m_a.java.entity.PickupPoint;
import ru.vsu.cs.iachnyi_m_a.java.repository.PickupPointRepository;

import java.util.List;
import java.util.Optional;

public class PickupPointRepositorySQL implements PickupPointRepository {
    @Override
    public Optional<PickupPoint> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public List<PickupPoint> findAll() {
        return List.of();
    }

    @Override
    public PickupPoint save(PickupPoint entity) {
        return null;
    }

    @Override
    public void delete(Long id) {

    }
}
