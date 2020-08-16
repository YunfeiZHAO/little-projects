for ((client=1; client<=$3; client++))
do
	RANDOM=$(head -1 /dev/urandom | od -N 2 | awk '{ print $3 }')
	FIX_RANDOM=$RANDOM
	echo "$FIX_RANDOM"
	./tcpclient $1 $2 $FIX_RANDOM &
	sleep 0.1
done

wait
