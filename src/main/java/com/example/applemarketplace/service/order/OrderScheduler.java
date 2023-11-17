package com.example.applemarketplace.service.order;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@Slf4j
@Service
@RequiredArgsConstructor
public class OrderScheduler {
    private final OrderService orderService;

    @Scheduled(cron = "0 * * * * *")
    @SchedulerLock(name = "delete_unpaid_orders_lock", lockAtLeastFor = "PT30S", lockAtMostFor = "PT5M")
    public void deleteUnpaidOrders() {

        log.info("Deleting unpaid orders lock woke up");

        orderService.deleteUnpaidOrders();

        log.info("Deleting unpaid orders completed");
    }
}
