package com.point.byon.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.point.byon.dto.OrdersDTO;
import com.point.byon.dto.UsersDTO;
import com.point.byon.entity.OrdersDetailsEntity;
import com.point.byon.entity.OrdersEntity;
import com.point.byon.entity.ProductsEntity;
import com.point.byon.entity.UsersEntity;
import com.point.byon.repository.OrdersDetailsRepository;
import com.point.byon.repository.OrdersRepository;
import com.point.byon.repository.ProductsRepository;
import com.point.byon.repository.UsersRepository;

@Service
public class IndexService {

	@Autowired
	UsersRepository usersRepo;
	@Autowired
	ProductsRepository productsRepo;
	@Autowired
	OrdersRepository ordersRepo;
	@Autowired
	OrdersDetailsRepository ordersDetailsRepo;
	
	@Transactional
	public List<String> prepare() {
		List<String> errorMessage = new ArrayList<>();

		UsersEntity savedUsersEntity = usersRepo.save(UsersEntity.builder()
															.id("hong")
															.password("hong1234")
															.name("홍길동")
															.role("USER")
															.build());
		if (savedUsersEntity == null) {
			errorMessage.add("유저 테이블 입력 실패");
		}
		
		ProductsEntity savedProductsEntity1 = productsRepo.save(ProductsEntity.builder()
															.name("실켓 레귤러핏 맨투맨 긴팔 티셔츠 23TL01")
															.price(1200)
															.build());
		/*ProductsEntity savedProductsEntity2 = productsRepo.save(ProductsEntity.builder()
															.name("GENERATION PP COLLARED SWEATSHIRT HEATHER GRAY")
															.price(79000)
															.build());*/
		if (savedProductsEntity1 == null/* || savedProductsEntity2 == null*/) {
			errorMessage.add("상품 테이블 입력 실패");
		}
		
		OrdersEntity savedOrdersEntity = ordersRepo.save(OrdersEntity.builder()
															.amount(1200)
															.build());
		if (savedOrdersEntity == null) {
			errorMessage.add("주문 테이블 입력 실패");
		}
		
		OrdersDetailsEntity savedOrdersDetailsEntity1 = ordersDetailsRepo.save(OrdersDetailsEntity.builder()
															.ordersEntity(savedOrdersEntity)
															.productsEntity(savedProductsEntity1)
															.build());
		/*OrdersDetailsEntity savedOrdersDetailsEntity2 = ordersDetailsRepo.save(OrdersDetailsEntity.builder()
															.ordersEntity(savedOrdersEntity)
															.productsEntity(savedProductsEntity2)
															.build());*/
		if (savedOrdersDetailsEntity1 == null /*|| savedOrdersDetailsEntity2 == null*/) {
			errorMessage.add("주문 상세 테이블 입력 실패");
		}

		return errorMessage;
	}
	
	public UsersDTO getUser() {
		List<UsersEntity> users = usersRepo.findAll();
		if (users == null) {
			return null;
		}
		return UsersDTO.toUsersDTO(users.get(0));
	}
	
	public OrdersDTO getOrder() {
		List<OrdersEntity> orders = ordersRepo.findAll();
		
		return OrdersDTO.toOrdersDTO(orders.get(0));
	}
}
