# chain

Для простоты транзакции генерируются с параметрами size и fee. 

Реализованы 2 алгоритма выбора транзакций в блок:
1. Сортировка по fee и выбор самых дорогих в пределах размера блока (100 единиц): функция buildBlockWithOrdering класса Miner.
2. Перебор всех возможных комбинаций транзакций и выбор max суммарной fee с учетом размера блока (100 единиц): функция doSomeWork класса Worker.
