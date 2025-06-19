package com.ejemplo;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

class PedidoServiceTest {

  @Test
  void testSinDescuentoYEnvioNormal() {
    DescuentoRepository mockRepo = mock(DescuentoRepository.class);
    when(mockRepo.obtenerPorcentaje("")).thenReturn(0.0);
    PedidoService service = new PedidoService(mockRepo);

    double total = service.calcularTotal(100, "", false);
    assertEquals(110.0, total);
  }

  @Test
  void testConDescuentoYEnvioExpress() {
    DescuentoRepository mockRepo = mock(DescuentoRepository.class);
    when(mockRepo.obtenerPorcentaje("PROMO10")).thenReturn(0.10);

    PedidoService service = new PedidoService(mockRepo);
    double total = service.calcularTotal(100, "PROMO10", true);
    assertEquals(110.0, total); // 100 - 10% + 20
  }

  @Test
  void testConDescuentoYEnvioNormal() {
    DescuentoRepository mockRepo = mock(DescuentoRepository.class);
    when(mockRepo.obtenerPorcentaje("PROMO10")).thenReturn(0.10);

    PedidoService service = new PedidoService(mockRepo);
    double total = service.calcularTotal(200, "PROMO10", false);
    assertEquals(190.0, total);
  }

  @Test
  void testSinDescuentoYEnvioExpress() {
    DescuentoRepository mockRepo = mock(DescuentoRepository.class);
    when(mockRepo.obtenerPorcentaje("")).thenReturn(0.0);

    PedidoService service = new PedidoService(mockRepo);
    double total = service.calcularTotal(150, "", true);
    assertEquals(170.0, total);
  }
}