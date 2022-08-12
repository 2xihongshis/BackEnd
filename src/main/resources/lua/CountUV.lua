-- pvKey, uvKey, ip
redis.call('incr', KEYS[1]);
redis.call('pfadd', KEYS[2], ARGV[1]);
return 0;
