set title "Insertion Complexity by Number of Comparisons"
set xlabel "Dictionary Size"
set ylabel "Average Number of Comparisons"
set pointsize 0.35
plot "BinarySearchTree.dat"   title "Binary Search Tree" with points lc rgb 'black', \
     "OrderedLinkedList.dat"  title "Ordered Linked List" with points lc rgb 'black'
