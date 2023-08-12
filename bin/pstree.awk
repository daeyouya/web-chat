BEGIN {
	pidscnt=0;
}
{
	pidmap[$2] = $3;
	pidline[$2] = $0;
	if ( $2 == PID )
	{
		pids[pidscnt++]=$2;
	}
}
END {
	startloop = 0;
	lastloop = 0;
	while ( pidscnt > lastloop )
	{
		startloop = lastloop;
		lastloop = pidscnt;
		for ( i = startloop ; i < lastloop ; i++ )
		{
			for ( pid in pidmap )
			{
				if ( pidmap[pid] == pids[i] )
				{
					pids[pidscnt++]=pid;
				}
			}
		}
	}

	for ( i = 0 ; i < pidscnt ; i++ )
	{
		PIDS = PIDS pids[i] " ";
		if ( STATUS ) print pidline[pids[i]];
	}

	print PIDS;
}
