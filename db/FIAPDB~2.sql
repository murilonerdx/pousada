SELECT * FROM tb_reservas
SELECT * FROM tb_quartos

SELECT 
    sqc_reservas.NEXTVAL 
FROM 
    dual;
    
DELETE tb_reservas

SELECT COUNT(*) FROM TB_RESERVAS WHERE NUMERO_QUARTO = 1 
AND DATA_ENTRADA <= TO_DATE('2020-08-31', 'yyyy-mm-dd') 
AND TO_DATE('2020-08-31', 'yyyy-mm-dd')  <= DATA_SAIDA

AND DATA_ENTRADA <= TO_DATE('2020-09-10', 'yyyy-mm-dd') 
AND TO_DATE('2020-09-10', 'yyyy-mm-dd')  <= DATA_SAIDA


SELECT COUNT(*) FROM TB_RESERVAS WHERE DATA_ENTRADA <= TO_DATE('01/09/2020', 'yyyy-mm-dd') AND TO_DATE('01/09/2020', 'yyyy-mm-dd')  <= DATA_SAIDA AND DATA_ENTRADA <= TO_DATE('01/09/2020', 'yyyy-mm-dd') AND TO_DATE('01/09/2020', 'yyyy-mm-dd')  <= DATA_SAIDA AND NUMERO_QUARTO = 1

SELECT COUNT(*) FROM TB_RESERVAS WHERE DATA_ENTRADA <= TO_DATE('01/09/2020', 'yyyy-mm-dd') AND TO_DATE('01/09/2020', 'yyyy-mm-dd')  <= DATA_SAIDA AND DATA_ENTRADA <= TO_DATE('01/09/2020', 'yyyy-mm-dd') AND TO_DATE('01/09/2020', 'yyyy-mm-dd')  <= DATA_SAIDA AND NUMERO_QUARTO = 1



