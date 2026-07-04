package mpack.repositories;

import org.springframework.data.jpa.repository.JpaRepository;

import mpack.entities.MyOrder;

public interface MyOrderRepository extends JpaRepository<MyOrder, Integer> {

}
