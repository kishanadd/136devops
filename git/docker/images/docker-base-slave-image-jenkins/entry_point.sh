#!/bin/bash
function shutdown {
  kill -s SIGTERM $NODE_PID
  wait $NODE_PID
}
NODE_PID=$!
trap shutdown SIGTERM SIGINT
exec "$@"