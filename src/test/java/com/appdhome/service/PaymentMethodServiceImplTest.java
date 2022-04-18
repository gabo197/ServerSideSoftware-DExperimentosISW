package com.appdhome.service;

import com.appdhome.entities.PaymentMethod;
import com.appdhome.repository.IPaymentMethodRepository;
import com.appdhome.services.impl.PaymentMethodServiceImpl;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
public class PaymentMethodServiceImplTest {

    @Mock
    private IPaymentMethodRepository paymentMethodRepository;
    @InjectMocks
    private PaymentMethodServiceImpl paymentMethodService;

    @Test
    public void saveTest() {
        PaymentMethod paymentMethod = new PaymentMethod(1L, "Efectivo");

        given(paymentMethodRepository.save(paymentMethod)).willReturn(paymentMethod);

        PaymentMethod savedPaymentMethod = null;
        try {
            savedPaymentMethod = paymentMethodRepository.save(paymentMethod);
        } catch (Exception e) {
        }

        assertThat(savedPaymentMethod).isNotNull();
        verify(paymentMethodRepository).save(any(PaymentMethod.class));
    }
    @Test
    void findByIdTest() throws Exception{
        Long id = 1L;
        PaymentMethod paymentMethod = new PaymentMethod(1L, "Efectivo");

        given(paymentMethodRepository.findById(id)).willReturn(Optional.of(paymentMethod));

        Optional<PaymentMethod> expected = null;
        expected = paymentMethodService.getById(id);
        assertThat(expected).isNotNull();
    }

    @Test
    void findAllTest() throws Exception {
        List<PaymentMethod> list = new ArrayList<>();
        list.add(new PaymentMethod(1L, "Efectivo"));
        list.add(new PaymentMethod(1L, "Transferencia"));
        list.add(new PaymentMethod(1L, "Tarjeta de Debito"));
        list.add(new PaymentMethod(1L, "Tarjeta de Credito"));
        given(paymentMethodRepository.findAll()).willReturn(list);
        List<PaymentMethod> expected = paymentMethodService.getAll();
        assertEquals(expected, list);
    }

    @Test
    void deleteTest() throws Exception {
        Long id = 1L;
        paymentMethodService.delete(id);
        verify(paymentMethodRepository, times(1)).deleteById(id);
    }
}
