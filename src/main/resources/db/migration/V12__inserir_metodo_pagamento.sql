UPDATE alugueis
SET metodo_pagamento = 
    CASE 
        WHEN id % 4 = 0 THEN 'CARTAO_CREDITO'
        WHEN id % 4 = 1 THEN 'DEBITO'
        WHEN id % 4 = 2 THEN 'GIFT_CARD'
        ELSE 'PIX'
    END
WHERE id BETWEEN 1 AND 20;
